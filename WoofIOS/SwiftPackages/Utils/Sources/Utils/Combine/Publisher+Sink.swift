import Combine

public extension Publisher {
    func pss_sink(_ receive: @escaping (Result<Output, Failure>) -> Void) -> AnyCancellable {
        sink(
            receiveCompletion: { completion in
                if case .failure(let error) = completion {
                    receive(.failure(error))
                }
            },
            receiveValue: { value in
                receive(.success(value))
            }
        )
    }
}
