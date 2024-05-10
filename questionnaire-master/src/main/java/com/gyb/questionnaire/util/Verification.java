package com.gyb.questionnaire.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 验证码
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Verification {
    private String code;
    private byte[] buffer; //存储图片字节数组
}
