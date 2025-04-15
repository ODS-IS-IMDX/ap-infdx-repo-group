// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.presentation.dto.reference;

import com.spatialid.app.common.validation.CheckFieldExist;

import lombok.Data;

/**
 * API利用者グループ参照APIが受け取るリクエストのクエリパラメータを格納するDTOクラス.
 * 
 * @author ukai jun
 * @version 1.1 2024/12/10
 */
@Data
@CheckFieldExist(fields = { "servicerId", "servicerGroupId" })
public class GetApiGroupsRequestDto {

    /**
     * 利用者システムID.
     */
    private String servicerId;

    /**
     * 利用者グループID.
     */
    private String servicerGroupId;
}
