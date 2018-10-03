package com.acis.assets.mgmt.ao;

import java.util.List;

import com.atlassian.activeobjects.external.ActiveObjects;

public interface AssetsConfigService {
	public AssetsCustomFieldsEntity createCustomFieldId(ActiveObjects ao, String fieldId);

	public List<AssetsCustomFieldsEntity> getAllCustomFieldsId(ActiveObjects ao);

	public List<AssetsCustomFieldsEntity> getCustomFieldId(ActiveObjects ao, String fieldId);

	public AssetsStatusEntity createStatusId(ActiveObjects ao, String id);

	public List<AssetsStatusEntity> getAllStatusIds(ActiveObjects ao);

	public List<AssetsStatusEntity> getStatusId(ActiveObjects ao, String id);

	public List<AssetTypesEntity> getAllAssetTypes(ActiveObjects ao);

	public List<AssetTypesEntity> getAssetType(ActiveObjects ao, String id);

	public AssetTypesEntity createAssetType(ActiveObjects ao, String name, String description, String iconUrl);

	public void updateAssetTypeAndFieldsMapping(ActiveObjects ao, AssetTypesEntity assetType, List<String> fieldIds);
}
