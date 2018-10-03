package com.acis.itsm.portal;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.atlassian.fugue.Either;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.pocketknife.api.commons.error.AnError;
import com.atlassian.servicedesk.api.ServiceDesk;
import com.atlassian.servicedesk.api.ServiceDeskService;
import com.atlassian.servicedesk.api.field.FieldId;
import com.atlassian.servicedesk.api.field.FieldInputValue;
import com.atlassian.servicedesk.api.request.CustomerRequest;
import com.atlassian.servicedesk.api.request.CustomerRequestChannelSource;
import com.atlassian.servicedesk.api.request.CustomerRequestCreateParameters;
import com.atlassian.servicedesk.api.request.ServiceDeskCustomerRequestService;
import com.atlassian.servicedesk.api.requesttype.RequestType;
import com.atlassian.servicedesk.api.requesttype.RequestTypeQuery;
import com.atlassian.servicedesk.api.requesttype.RequestTypeService;
import com.atlassian.servicedesk.api.util.paging.PagedResponse;

@SuppressWarnings("serial")
public class PortalAction extends JiraWebActionSupport {

	@Autowired
	private ServiceDeskCustomerRequestService customerRequestService;

	@Autowired
	private RequestTypeService requestTypeService;
	@Autowired
	private ServiceDeskService serviceDeskService;

	private String sdTicket;
	private int portalId;

	public String getSdTicket() {
		return sdTicket;
	}

	public void setSdTicket(String sdTicket) {
		this.sdTicket = sdTicket;
	}

	public int getPortalId() {
		return portalId;
	}

	public void setPortalId(int portalId) {
		this.portalId = portalId;
	}

	public PortalAction() {
	}

	protected void doValidation() {
		System.out.println("##################### doValidation #######");
		for (Enumeration e = getHttpRequest().getParameterNames(); e.hasMoreElements();) {
			String n = (String) e.nextElement();
			String[] vals = getHttpRequest().getParameterValues(n);
			System.out.println("name " + n + ": " + vals[0]);
		}

		// invalidInput() checks for error messages, and errors too.
		if (invalidInput()) {
			for (Iterator it = getErrorMessages().iterator(); it.hasNext();) {
				String msg = (String) it.next();
				System.out.println("Error message during validation: " + msg);
			}

			for (Iterator it2 = getErrors().entrySet().iterator(); it2.hasNext();) {
				Map.Entry entry = (Map.Entry) it2.next();
				System.out.println("Error during validation: field=" + entry.getKey() + ", error=" + entry.getValue());
			}
		}
		System.out.println("#####################doValidation - end #######");
	}

	/**
	 * This method is always called when this Action's .jspa URL is invoked if there
	 * were no errors in doValidation().
	 */
	@SuppressWarnings("unused")
	protected String doExecute() throws Exception {
		System.out.println("##################### doExecute #######");
		HttpServletRequest req = getHttpRequest();
		String _summary = req.getParameter("summary-input");
		String _description = req.getParameter("description-input");
		ApplicationUser loggedInUser = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser();
		int portalID = 1;
		this.setPortalId(portalID);
		if (_summary != null && _description != null) {
			Project helpdesk = ComponentAccessor.getProjectManager().getProjectObjByKey("IHD");
			Either<AnError, ServiceDesk> sd = serviceDeskService.getServiceDeskForProject(loggedInUser, helpdesk);
			ServiceDesk serviced = sd.getOrNull();
			if (sd == null) {
				addErrorMessage("No Service desk found for " + helpdesk.getName() + " Project");
				return "error";
			}

			RequestTypeQuery requestTypeBuilder = requestTypeService.newQueryBuilder().requestType(1).build();
			Either<AnError, PagedResponse<RequestType>> rt = requestTypeService.getRequestTypes(loggedInUser,
					requestTypeBuilder);
			List<RequestType> requestTypes = rt.getOrNull().getResults();
			if (requestTypes != null) {
				System.out.println("Request Name: " + requestTypes.get(0).getName());
			} else {
				addErrorMessage("No Request Type Found.");
				return "error";
			}

			CustomerRequestCreateParameters reqBuilder = customerRequestService.newCreateBuilder().serviceDesk(serviced)
					.requestType(requestTypes.get(0))
					.fieldValue(FieldId.withId("summary"), FieldInputValue.withValue(_summary))
					.fieldValue(FieldId.withId("description"), FieldInputValue.withValue(_description))
					.customerRequestChannelSource(CustomerRequestChannelSource.PORTAL).build();

			Either<AnError, CustomerRequest> cr = customerRequestService.createCustomerRequest(loggedInUser,
					reqBuilder);
			CustomerRequest servicedesk = cr.getOrNull();
			if (servicedesk != null) {
				Issue newIssue = servicedesk.getIssue();
				this.setSdTicket(newIssue.getKey());
				System.out.println("Service Desk Ticket Created: " + newIssue);
				return "success";
			} else {
				System.out.println("Error Message: " + cr.left().getOrNull().getMessage());
				addErrorMessage(cr.left().getOrNull().getMessage().getMessage());
				return "error";
			}

		}
		System.out.println("##################### doExecute - end #######");
		return "input";
	}

}
