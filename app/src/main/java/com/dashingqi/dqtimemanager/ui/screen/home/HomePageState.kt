package com.dashingqi.dqtimemanager.ui.screen.home

/**
 * home-state
 * @author zhangqi61
 * @since 2023/8/28
 */

/**
 * 时间模式
 */
enum class TimeMode {
    PM,
    AM
}

/**
 * UI状态
 * @property timeMode TimeMode
 * @property isImmersionShow Boolean
 * @constructor
 */
data class UiState(
    val timeMode: TimeMode = TimeMode.PM,
    val isImmersionShow: Boolean = false
)

/**
 * 时间
 * @property hour Int
 * @property minute Int
 * @property second Int
 * @constructor
 */
data class TimeState(
    var hour: Int = 0,
    var minute: Int = 0,
    var second: Int = 0
)

/**
 * 日期
 * @property date String
 * @property week String
 * @constructor
 */
data class DateState(
    var date: String = "",
    var week: String = ""
)