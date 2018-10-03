package com.acis.assets.mgmt.ao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.atlassian.activeobjects.external.ActiveObjects;

import net.java.ao.Query;

@Service
public class AssetsConfigServiceImpl implements AssetsConfigService {

	public AssetsConfigServiceImpl() {
		super();

	}

	@Override
	public AssetsCustomFieldsEntity createCustomFieldId(ActiveObjects ao, String fieldId) {
		AssetsCustomFieldsEntity entity = ao.create(AssetsCustomFieldsEntity.class);
		entity.setCustomFieldId(fieldId);
		entity.save();

		return entity;
	}

	@Override
	public List<AssetsCustomFieldsEntity> getAllCustomFieldsId(ActiveObjects ao) {
		AssetsCustomFieldsEntity[] allRecords = ao.find(AssetsCustomFieldsEntity.class);
		if (allRecords != null)
			return Arrays.asList(allRecords);
		else
			return null;
	}

	@Override
	public List<AssetsCustomFieldsEntity> getCustomFieldId(ActiveObjects ao, String id) {
		AssetsCustomFieldsEntity[] fieldRecords = ao.find(AssetsCustomFieldsEntity.class, Query.select().where("ID = ?", id));
		if (fieldRecords != null)
			return Arrays.asList(fieldRecords);
		else
			return null;
	}

	@Override
	public AssetsStatusEntity createStatusId(ActiveObjects ao, String statusId) {

		AssetsStatusEntity entity = ao.create(AssetsStatusEntity.class);
		entity.setStatusId(statusId);
		entity.save();

		return entity;
	}

	@Override
	public List<AssetsStatusEntity> getAllStatusIds(ActiveObjects ao) {

		AssetsStatusEntity[] allRecords = ao.find(AssetsStatusEntity.class);
		if (allRecords != null)
			return Arrays.asList(allRecords);
		else
			return null;
	}

	@Override
	public List<AssetsStatusEntity> getStatusId(ActiveObjects ao, String id) {

		AssetsStatusEntity[] fieldRecords = ao.find(AssetsStatusEntity.class, Query.select().where("ID = ?", id));
		if (fieldRecords != null)
			return Arrays.asList(fieldRecords);
		else
			return null;

	}

	@Override
	public List<AssetTypesEntity> getAssetType(ActiveObjects ao, String id) {

		AssetTypesEntity[] assetTypeRecords = ao.find(AssetTypesEntity.class, Query.select().where("ID = ?", id));
		if (assetTypeRecords != null)
			return Arrays.asList(assetTypeRecords);
		else
			return null;

	}

	@Override
	public List<AssetTypesEntity> getAllAssetTypes(ActiveObjects ao) {

		AssetTypesEntity[] allRecords = ao.find(AssetTypesEntity.class);
		if (allRecords != null)
			return Arrays.asList(allRecords);
		else
			return null;
	}

	@Override
	public AssetTypesEntity createAssetType(ActiveObjects ao, String name, String description, String iconUrl) {

		AssetTypesEntity entity = ao.create(AssetTypesEntity.class);
		entity.setAssetTypeName(name);
		entity.setAssetTypeDescription(description);
		entity.setAssetTypeIconUrl(iconUrl);
		entity.save();

		return entity;
	}

	@Override
	public void updateAssetTypeAndFieldsMapping(ActiveObjects ao, AssetTypesEntity assetType, List<String> fieldIds) {
		/*
		 * String placeholderCommaList =
		 * Joiner.on(", ").join(Iterables.transform(fieldIds, Functions.constant("?")));
		 * Object[] matchValuesArray = Iterables.toArray(fieldIds, Object.class); Query
		 * query = Query.select().where("ID IN (" + placeholderCommaList + ")",
		 * matchValuesArray); System.out.println("query: " + query);
		 * AssetsCustomFieldsEntity[] fieldEntities =
		 * ao.find(AssetsCustomFieldsEntity.class, query);
		 * System.out.println("fieldEntities: " + fieldEntities.length); for
		 * (AssetsCustomFieldsEntity entity : fieldEntities) {
		 * entity.setAssetTypesEntity(assetType); entity.save(); }
		 */
		CustomfieldsMappingEntity[] fieldsEntitis = assetType.getCustomfieldsMappingEntity();
		Set<String> fields = new HashSet<String>();
		for (CustomfieldsMappingEntity fieldEntity : fieldsEntitis) {
			fields.add(fieldEntity.getCustomFieldId());
		}

		for (String id : fieldIds) {
			if (!fields.contains(id)) {
				CustomfieldsMappingEntity entity = ao.create(CustomfieldsMappingEntity.class);
				entity.setAssetTypesEntity(assetType);
				entity.setCustomFieldId(id);
				entity.save();
			}
		}

	}

}
