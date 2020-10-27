package com.boycoder.kotlinjetpackinaction.entity

/**
 *
 * @Description:
 * @Author:         duanlei
 * @CreateDate:     2020/10/26 17:12
 */

data class User(
        var id: String? = null,
        var login: String? = null,
        var avatar_url: String? = null,
        var name: String? = null,
        var company: String? = null,
        var blog: String? = null,
        var lastRefresh: String? = null
)