package org.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 删除/禁用注解
 * <p>
 * 使用此注解标记的类将在mod加载过程中被跳过，不再会被注册或初始化。
 * 这适用于临时禁用某个功能模块而不必删除整个类文件的情况。
 * <p>
 * 使用方法：
 * 在需要禁用的类文件头部添加 @Delete 注解，并可选择提供禁用原因。
 * <p>
 * 示例：
 * {@code
 * @Delete(reason = "此功能已被新版本替代，将在下个版本移除")
 * public class OldFeature {
 *     // 类内容
 * }
 * }
 *
 * @author YL
 * @version 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Delete {
    /**
     * 提供禁用此类的原因（可选）
     *
     * @return 禁用原因的字符串描述
     */
    String reason() default "";
}
