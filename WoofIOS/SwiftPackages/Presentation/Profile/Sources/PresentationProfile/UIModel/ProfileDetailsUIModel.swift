import Foundation

public struct ProfileDetailsUIModel: Hashable {
    public let name: String
    public let summary: ProfileSummaryUIModel?
    public let heroTags: [String]
    public let content: ProfileContentUIModel

    public init(name: String, summary: ProfileSummaryUIModel?, heroTags: [String], content: ProfileContentUIModel) {
        self.name = name
        self.summary = summary
        self.heroTags = heroTags
        self.content = content
    }
}
