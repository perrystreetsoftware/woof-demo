import Resources

public struct OrgNavigationHeaderActionItem {
    public let icon: ImageAsset
    public let contentDescription: String
    public let colorRole: IconColorRole
    public let onTap: () -> Void

    public init(icon: ImageAsset, contentDescription: String, colorRole: IconColorRole, onTap: @escaping () -> Void) {
        self.icon = icon
        self.contentDescription = contentDescription
        self.colorRole = colorRole
        self.onTap = onTap
    }
}
