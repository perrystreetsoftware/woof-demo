import Combine
import DesignSystem
import SwiftUI

public struct ErrorAdapter: ViewModifier {
    @State private var errorToast: ErrorToast?

    private let sources: [ErrorSource]
    private let errorMapper: ErrorToToastMapping

    public init(sources: [ErrorSource], errorMapper: ErrorToToastMapping) {
        self.sources = sources
        self.errorMapper = errorMapper
    }

    public func body(content: Content) -> some View {
        content
            .overlay {
                TemplateOverlayTop {
                    OrgToastHost(message: errorToast?.message, onDismiss: onDismiss)
                }
            }
            .onReceive(mergedErrors) { error in
                errorToast = error.flatMap { errorMapper($0) }
            }
    }

    private var mergedErrors: AnyPublisher<Swift.Error?, Never> {
        Publishers.MergeMany(sources.map(\.errors)).eraseToAnyPublisher()
    }

    private func onDismiss() {
        sources.forEach { $0.clearLastError() }
    }
}

public extension View {
    func errorAdapter(sources: [ErrorSource], errorMapper: ErrorToToastMapping) -> some View {
        modifier(ErrorAdapter(sources: sources, errorMapper: errorMapper))
    }
}
