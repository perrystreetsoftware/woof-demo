import SwiftUI

public struct AccountAdapter: View {
    @StateObject private var viewModel: AccountViewModel

    public init() {
        _viewModel = .init()
    }

    public var body: some View {
        AccountScreen(state: viewModel.state)
            .onAppear {
                viewModel.onViewAppear()
            }
    }
}
