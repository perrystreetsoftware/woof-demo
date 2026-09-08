import Models

public enum AccountDetailRowUIModel: Hashable {
    case breed(String)
    case age(Int)
    case size(DogSize)
    case neighborhood(String)
    case favoriteActivity(String)
}
