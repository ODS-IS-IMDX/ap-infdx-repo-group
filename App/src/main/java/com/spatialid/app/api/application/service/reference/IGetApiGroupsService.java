// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.application.service.reference;

import org.springframework.stereotype.Service;

import com.spatialid.app.api.presentation.dto.reference.GetApiGroupsRequestDto;
import com.spatialid.app.api.presentation.dto.reference.GetApiGroupsResponseDto;

/**
 * API利用者グループ参照APIのビジネスロジックを実装するServiceインターフェイス.
 * 
 * @author ukai jun
 * @version 1.1 2024/12/11
 */
@Service
public interface IGetApiGroupsService {

    /**
     * API利用者グループ情報を取得するメソッド.
     * 
     * @param requestDto API利用者グループ参照APIが受け取るリクエストDTO.
     * @return API利用者グループ参照APIが返却するレスポンスDTO.
     */
    GetApiGroupsResponseDto getApiGroupsInfo(GetApiGroupsRequestDto requestDto);

}
