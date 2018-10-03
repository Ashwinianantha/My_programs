package com.acis.itsm.utils;

import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;

public class GeneralOsgiImports {
	/******************************
    // JIRA
    ******************************/
    @ComponentImport com.atlassian.jira.issue.IssueManager issueManager;
    @ComponentImport com.atlassian.jira.security.PermissionManager permissionManager;
    @ComponentImport com.atlassian.jira.user.util.UserManager userManager;
    @ComponentImport com.atlassian.jira.util.I18nHelper i18nHelper;
    @ComponentImport com.atlassian.jira.util.I18nHelper.BeanFactory i18nBeanFactory;
    @ComponentImport com.atlassian.jira.bc.issue.label.LabelService labelService;
    
    /******************************
    // JSD
    ******************************/
    @ComponentImport com.atlassian.servicedesk.api.request.ServiceDeskCustomerRequestService customerRequestService;
    @ComponentImport com.atlassian.servicedesk.api.ServiceDeskService serviceDeskService;
    @ComponentImport com.atlassian.servicedesk.api.requesttype.RequestTypeService requestTypeService;
    @ComponentImport com.atlassian.servicedesk.api.ServiceDeskManager servicedeskManager;
}
