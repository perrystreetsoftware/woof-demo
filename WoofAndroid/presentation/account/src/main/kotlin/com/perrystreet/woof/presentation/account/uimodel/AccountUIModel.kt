package com.perrystreet.woof.presentation.account.uimodel

data class AccountUIModel(
    val name: String,
    val photoUrl: String,
    val summary: AccountSummaryUIModel,
    val bio: String,
    val personality: List<String>,
    val rows: List<AccountDetailRowUIModel>,
)
