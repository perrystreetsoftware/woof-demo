import SwiftUI

public struct OrgToastHost: View {
    @Environment(\.theme) private var theme

    private let message: String?
    private let onDismiss: () -> Void

    public init(message: String?, onDismiss: @escaping () -> Void) {
        self.message = message
        self.onDismiss = onDismiss
    }

    public var body: some View {
        VStack(spacing: 0) {
            switch message {
            case .none:
                EmptyView()
            case .some(let message):
                MolToast(text: message)
                    .transition(.move(edge: .top).combined(with: .opacity))
            }
        }
        .animation(.easeInOut(duration: theme.motion.durationShort), value: message)
        .task(id: message) {
            guard message != nil else { return }
            try? await Task.sleep(for: .seconds(theme.motion.toastDuration))
            guard !Task.isCancelled else { return }
            onDismiss()
        }
    }
}
