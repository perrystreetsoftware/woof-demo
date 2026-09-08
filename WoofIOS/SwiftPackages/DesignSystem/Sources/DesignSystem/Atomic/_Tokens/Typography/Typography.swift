import SwiftUI

public struct Typography {
    public let display: Display
    public let subhead: Subhead
    public let body: Body

    public struct Display {
        public let h1: Font
        public let h2: Font
        public let h3: Font
        public let h4: Font
        public let h5: Font
        public let h6: Font
    }

    public struct Subhead {
        public let p1: Font
        public let p2: Font
        public let p3: Font
    }

    public struct Body {
        public let p1: Font
        public let p2: Font
        public let p3: Font
        public let p4: Font
    }
}
