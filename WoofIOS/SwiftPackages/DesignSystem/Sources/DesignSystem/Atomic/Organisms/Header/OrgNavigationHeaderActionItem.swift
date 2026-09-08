import Foundation

public struct OrgNavigationHeaderActionItem {
    public let role: IconButtonRole
    public let onTap: () -> Void
    public let isActive: Bool

    public init(role: IconButtonRole, onTap: @escaping () -> Void, isActive: Bool = false) {
        self.role = role
        self.onTap = onTap
        self.isActive = isActive
    }
}
