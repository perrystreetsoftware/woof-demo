import Foundation

public enum ProfileSectionUIModel: Hashable {
    case about(name: String, bio: String)
    case personality(tags: [String])
    case details(rows: [ProfileDetailRowUIModel])
}
