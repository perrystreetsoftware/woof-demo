import Resources

public struct OrgOverflowMenuItem {
    public let text: String
    public let icon: ImageAsset
    public let onTap: () -> Void
    public let isDestructive: Bool

    public init(text: String, icon: ImageAsset, onTap: @escaping () -> Void, isDestructive: Bool = false) {
        self.text = text
        self.icon = icon
        self.onTap = onTap
        self.isDestructive = isDestructive
    }
}
