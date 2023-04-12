//
//  ViewExtension.swift
//  iosApp
//
//  Created by Azzam Habib on 2/7/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI


extension View {
    func addBorder<S>(_ content: S, width: CGFloat = 1, cornerRadius: CGFloat) -> some View where S : ShapeStyle {
        let roundedRect = RoundedRectangle(cornerRadius: cornerRadius)
        return clipShape(roundedRect)
             .overlay(roundedRect.strokeBorder(content, lineWidth: width))
    }
    
    func customDialog<DialogContent: View>(
        isShowing: Bool,
        @ViewBuilder dialogContent: @escaping () -> DialogContent
      ) -> some View {
        self.modifier(CustomDialog(isShowing: isShowing, dialogContent: dialogContent))
      }

}
