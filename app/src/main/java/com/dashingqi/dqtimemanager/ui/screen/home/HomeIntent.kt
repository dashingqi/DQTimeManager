package com.dashingqi.dqtimemanager.ui.screen.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

/**
 * Home-Intent-ViewModel
 * @author zhangqi61
 * @since 2023/8/28
 */
class HomeViewModel : ViewModel() {
    private var _dateState = MutableStateFlow(DateState())
    val dateState = _dateState


    private var _timeState = MutableStateFlow(TimeState())
    val timeState = _timeState

    private var _uiState = MutableStateFlow(UiState())
    val uiState = _uiState

    init {
        initTime()
    }

    /**
     * 初始化时间
     */
    private fun initTime() {
        LocalDateTime.now().apply {
            _timeState.also { timeState ->
                timeState.value = timeState.value.copy(hour = hour, minute = minute, second = second)

            }
        }
        updateTime()
    }

    /**
     * 更新时间逻辑
     */
    private fun updateTime() {
        viewModelScope.launch(Dispatchers.IO) {
            _timeState.apply {
                while (true) {
                    val nowSecond = value.second + 1
                    if (nowSecond == 60) {
                        value = value.copy(second = 0)
                        val nowMinute = value.minute + 1
                        // 分钟满了
                        if (nowMinute == 60) {
                            value = value.copy(minute = 0)
                            val nowHour = value.hour + 1
                            val timeMode = if (nowHour > 12) {
                                TimeMode.PM
                            } else {
                                TimeMode.AM
                            }

                            _uiState.value = _uiState.value.copy(timeMode = timeMode)
                            value = if (nowHour == 24) {
                                value.copy(hour = 0)
                            } else {
                                value.copy(hour = nowHour)
                            }
                        } else {
                            value = value.copy(minute = nowMinute)
                        }
                    } else {
                        value = value.copy(second = nowSecond)
                    }
                    delay(1000)
                }
            }
        }
    }
}