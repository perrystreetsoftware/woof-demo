import DesignSystem

extension HomeTabUIModel {
    func toBottomNavigationRole() -> BottomNavigationRole {
        switch self {
        case .browse: .browse
        case .favorites: .favorites
        case .account: .account
        }
    }
}
