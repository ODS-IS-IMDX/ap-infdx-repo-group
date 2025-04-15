// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.presentation.dto.reference;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * API利用者グループ参照APIが返却するレスポンスを格納するDTOクラス.
 * <p>
 * 子DTOクラスとして@link{ServicerGroupDto}を持つ.
 * 
 * @author ukai jun
 * @version 1.1 2024/12/10
 */
@Data
@AllArgsConstructor
@Builder
public class GetApiGroupsResponseDto {

    /**
     * 利用者システムIDリスト.
     */
    private List<String> servicerIdList;
    
    /**
     * 利用者グループ情報リスト.
     */
    private List<ServicerGroupDto> servicerGroupList;
    
}
