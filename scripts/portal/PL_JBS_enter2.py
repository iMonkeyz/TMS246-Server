# id 6 (P1toP2), field 867236100
sm.createQuestWithQRValue(64202, "event2_camera=0;event2_wall=1")
sm.createQuestWithQRValue(64202, "event2_camera=1;event2_wall=1")
sm.lockInGameUI(True, False)
sm.forcedInput(0)
sm.sendDelay(1500)
sm.lockInGameUI(False, True)
sm.lockInGameUI(True, False)
sm.forcedInput(0)
sm.zoomCamera(3000, 1000, 3000, 177, 385)
sm.sendDelay(1500)
sm.lockInGameUI(False, True)
sm.createQuestWithQRValue(16119, "")
sm.createQuestWithQRValue(16150, "")