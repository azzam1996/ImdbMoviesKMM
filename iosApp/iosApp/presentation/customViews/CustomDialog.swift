//
//  CustomDialog.swift
//  iosApp
//
//  Created by Azzam Habib on 2/13/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CustomDialog<DialogContent: View>: ViewModifier {
  var isShowing: Bool
  let dialogContent: DialogContent

  init(isShowing: Bool,
        @ViewBuilder dialogContent: () -> DialogContent) {
    self.isShowing = isShowing
     self.dialogContent = dialogContent()
  }

  func body(content: Content) -> some View {

    ZStack {
      content
      if isShowing {
        Rectangle().foregroundColor(Color.black.opacity(0.6))
        ZStack {
          dialogContent
        }
        .padding(40)
      }
    }
  }
}
