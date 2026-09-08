package com.perrystreet.woof.presentation.grid.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.designsystem.atomic.organisms.card.OrgPhotoCard
import com.perrystreet.woof.presentation.common.image.AsyncImageStateExtensions.rememberAsyncImageState
import com.perrystreet.woof.presentation.grid.uimodel.DogCellUIModel
import com.perrystreet.woof.resources.R

@Composable
internal fun DogCell(
    cell: DogCellUIModel,
    onCellAppear: (DogCellUIModel) -> Unit,
    onCellTap: (DogCellUIModel) -> Unit,
) {
    LaunchedEffect(cell.id) {
        onCellAppear(cell)
    }

    OrgPhotoCard(
        title = cell.name,
        imageState = rememberAsyncImageState(url = cell.photoUrl),
        contentDescription = stringResource(R.string.accessibility_dog_photo, cell.name),
        onTap = { onCellTap(cell) },
    )
}
