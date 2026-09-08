import Foundation

public struct AccountUIModel: Hashable {
    public let name: String
    public let photoUrl: String
    public let summary: AccountSummaryUIModel
    public let bio: String
    public let personality: [String]
    public let rows: [AccountDetailRowUIModel]

    public init(
        name: String,
        photoUrl: String,
        summary: AccountSummaryUIModel,
        bio: String,
        personality: [String],
        rows: [AccountDetailRowUIModel]
    ) {
        self.name = name
        self.photoUrl = photoUrl
        self.summary = summary
        self.bio = bio
        self.personality = personality
        self.rows = rows
    }
}
