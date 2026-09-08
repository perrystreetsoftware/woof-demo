import SwiftUI

public struct ProfilePagerAdapter: View {
    @StateObject private var viewModel: ProfilePagerViewModel

    public init(dogId: Int) {
        _viewModel = .init(arg1: dogId)
    }

    public var body: some View {
        ProfilePagerScreen(state: viewModel.state) { page in
            ProfileViewAdapter(page: page)
        }
        .onAppear {
            viewModel.onViewAppear()
        }
    }
}
