import sys
import os
import random


def decide(URL,noFunAI):
    ans = noFunAI.predict([URL])
    # if the link fits in a Entertainment category then Block
    if str(ans[0]) == 'Recreation' or str(ans[0]) == 'Sports' or str(ans[0]) == 'Shopping' or  str(ans[0]) == 'Games' or str(ans[0]) == 'Adult' or str(ans[0]) == 'Home':
        return 1
    else:
        return 2
