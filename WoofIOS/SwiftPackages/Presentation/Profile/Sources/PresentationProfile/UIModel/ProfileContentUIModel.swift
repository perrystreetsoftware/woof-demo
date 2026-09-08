import Foundation

public enum ProfileContentUIModel: Hashable {
    case loading
    case visible(sections: [ProfileSectionUIModel])
}
