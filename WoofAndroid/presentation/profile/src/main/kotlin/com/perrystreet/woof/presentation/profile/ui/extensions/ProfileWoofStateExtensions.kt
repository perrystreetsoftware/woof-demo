package com.perrystreet.woof.presentation.profile.ui.extensions

import androidx.annotation.DrawableRes
import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole
import com.perrystreet.woof.presentation.profile.viewmodel.ProfileWoofViewModel
import com.perrystreet.woof.resources.R

object ProfileWoofStateExtensions {
    @DrawableRes
    fun ProfileWoofViewModel.State.woofIconRes(): Int = when (hasWoofed) {
        true -> R.drawable.ic_paw_filled
        false -> R.drawable.ic_paw_outline
    }

    fun ProfileWoofViewModel.State.woofColorRole(): IconColorRole = when (hasWoofed) {
        true -> IconColorRole.Primary
        false -> IconColorRole.OnScrim
    }
}
