// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.presentation.dto.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * API利用者グループ登録APIが返却するレスポンスを格納するDTOクラス.
 * 
 * @author ukai jun
 * @version 1.1 2024/11/26
 */
@Data
@Builder
@AllArgsConstructor
public class RegApiGroupsResponseDto {

    /**
     * 利用者グループID.
     */
    private String servicerGroupId;
}
