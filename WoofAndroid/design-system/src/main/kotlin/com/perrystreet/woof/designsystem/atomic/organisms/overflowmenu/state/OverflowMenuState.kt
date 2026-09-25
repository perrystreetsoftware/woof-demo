package com.perrystreet.woof.designsystem.atomic.organisms.overflowmenu.state

import com.perrystreet.woof.designsystem.atomic.atoms.icon.roles.IconColorRole

enum class OverflowMenuState {
    Default {
        override val isExpanded = false
        override val colorRole = IconColorRole.OnScrim
    },
    Expanded {
        override val isExpanded = true
        override val colorRole = IconColorRole.Primary
    },
    ;

    abstract val isExpanded: Boolean
    abstract val colorRole: IconColorRole
}
