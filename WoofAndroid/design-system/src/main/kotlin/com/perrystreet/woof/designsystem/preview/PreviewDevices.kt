package com.perrystreet.woof.designsystem.preview

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Phone", device = Devices.PIXEL_7, showSystemUi = true)
@Preview(name = "Tablet", device = Devices.PIXEL_TABLET, showSystemUi = true)
annotation class PreviewDevices
