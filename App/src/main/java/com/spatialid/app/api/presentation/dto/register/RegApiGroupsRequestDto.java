// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.presentation.dto.register;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * API利用者グループ登録APIが受け取るリクエストを格納するDtoクラス.
 * 
 * @author ukai jun
 * @version 1.1 2024/11/26
 */
@Data
public class RegApiGroupsRequestDto {

    /**
     * 利用者システムID（リスト）.
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
