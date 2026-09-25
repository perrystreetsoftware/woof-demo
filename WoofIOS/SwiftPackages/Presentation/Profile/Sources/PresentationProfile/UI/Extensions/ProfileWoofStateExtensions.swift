import DesignSystem
import Resources

extension ProfileWoofViewModel.State {
    var woofIcon: ImageAsset {
        switch hasWoofed {
        case true: Asset.Icons.pawFilled
        case false: Asset.Icons.pawOutline
        }
    }

    var woofColorRole: IconColorRole {
        switch hasWoofed {
        case true: .primary
        case false: .onScrim
        }
    }
}
