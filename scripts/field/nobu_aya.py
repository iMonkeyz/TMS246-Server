# id 811000010 (Momiji Hills : Master Room), field 811000010
sm.lockInGameUI(True, True)
sm.hideUser(True)
sm.spawnNpc(9130105, 139, 161)
sm.showNpcSpecialActionByTemplateId(9130105, "summon", 0)
sm.spawnNpc(9130106, 483, 170)
sm.showNpcSpecialActionByTemplateId(9130106, "summon", 0)
sm.blind(True, 255, 0, 0, 0, 0)
sm.sendDelay(1200)
sm.blind(False, 0, 0, 0, 0, 1000)
sm.sendDelay(1400)
sm.blind(True, 200, 0, 0, 0, 1300)
sm.sendDelay(1600)
sm.sayMonologue("Fine. As you probably heard, I was given the mission to go spy on Princess No. ", 0)
sm.sayMonologue("Before going undercover, Nobukane gave me the family's holy artifact. ", 1)
sm.blind(False, 0, 0, 0, 0, 1300)
sm.sendDelay(1600)
sm.setSpeakerType(3)
sm.setParam(5)
sm.setInnerOverrideSpeakerTemplateID(9130105) # Nobukane
sm.sendNext("I'm sorry... ")
sm.setInnerOverrideSpeakerTemplateID(9130106) # Ayame
sm.sendSay("It's all right. My life here was pretty great, but now I have a job to do. Thanks for the memories. ")
sm.setInnerOverrideSpeakerTemplateID(9130105) # Nobukane
sm.sendSay("Why do you speak as if we won't see each other again? Here, take this. It will guide you back to us. Take it. ")
sm.setInnerOverrideSpeakerTemplateID(9130106) # Ayame
sm.sendSay("Is this...?")
sm.setInnerOverrideSpeakerTemplateID(9130105) # Nobukane
sm.sendSay("This mirror has been passed down through generations of our family. Legends say that it will show you the way to overcome any hardship. I'm fairly certain it will help you. ")
sm.sendSay("You are family. We are family. ")
sm.sendSay("Princess No should know about this mirror as well. Tell her that you were here to steal it from us. ")
sm.setInnerOverrideSpeakerTemplateID(9130106) # Ayame
sm.sendSay("...Thank you. Thank you so much, Mr. Nobukane! ")
sm.setInnerOverrideSpeakerTemplateID(9130105) # Nobukane
sm.sendSay("Just come back. Come back safe. We will all be waiting for you. ")
sm.blind(True, 200, 0, 0, 0, 1300)
sm.sendDelay(1600)
sm.sayMonologue("I took the mirror and went...", 0)
sm.sayMonologue("...to Princess No, my previous master.", 0)
sm.sayMonologue("As expected, she tried to get rid of me at first, but changed her tune when she saw the mirror.", 0)
sm.sayMonologue("She took me in but she still didn't fully trust me. She kept sending me further and further away on all these 'missions'.", 0)
sm.sayMonologue("And while I was gone... the Nobukane family was exterminated by Oda Nobunaga, and I heard that he kidnapped her.", 0)
sm.sayMonologue("I wanted to go right away, to see for myself, but... But there was nothing I could do. ", 1)
sm.sayMonologue("And then came that day...", 1)
sm.hideUser(False)
sm.blind(False, 0, 0, 0, 0, 1300)
sm.sendDelay(1600)
sm.blind(True, 255, 0, 0, 0, 500)
sm.sendDelay(500)
sm.lockInGameUI(False, True)
sm.warp(811000011)