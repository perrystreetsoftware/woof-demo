import Models

public enum ProfileDetailRowUIModel: Hashable {
    case breed(String)
    case age(Int)
    case size(DogSize)
    case neighborhood(String)
    case favoriteActivity(String)
}
