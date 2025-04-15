// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.presentation.dto.reference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * API利用者グループ情報を格納するDTOクラス.
 * <p>
 * 親DTOクラスとして@link{GetApiGroupsResponseDto}を持つ.
 * 
 * @author ukai jun
 * @version 1.1 2024/12/10
 */
@Data
@AllArgsConstructor
@Builder
public class ServicerGroupDto {

    /**
     * 利用者グループID.
     */
    private String servicerGroupId;
    
    /**
     * 利用者グループ名.
     */
    private String servicerGroupName;
    
    /**
     * 概要.
     */
    private String overview;

}
