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
        .padding(.top, theme.padding.screenTopRegular)
        .padding(.horizontal, theme.padding.screenHorizontal)
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .animation(.easeInOut(duration: theme.motion.durationShort), value: message)
        .allowsHitTesting(false)
        .task(id: message) {
            guard message != nil else { return }
            try? await Task.sleep(for: .seconds(theme.motion.toastDuration))
            guard !Task.isCancelled else { return }
            onDismiss()
        }
    }
}
