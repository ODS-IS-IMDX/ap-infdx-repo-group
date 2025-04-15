// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.presentation.dto.update;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 *  API利用者グループ更新APIへのリクエストを格納するDTOクラス.
 *  
 *  @author ukai jun
 *  @version 1.1 2024/11/28
 */
@Data
@Builder
@AllArgsConstructor
public class UpdApiGroupsRequestDto {

    /**
     * 利用者システムID（リスト）
     */
    @NotEmpty
    private List<@NotEmpty String> servicerIdList;

    /**
     * 利用者グループ名.
     */
    @NotEmpty
    @Size(max = 100)
    private String servicerGroupName;
    
    /**
     * 概要.
     */
    @Size(max = 1000)
    private String overview;
}
