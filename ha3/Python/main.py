# diskret ereignisorientiere Simulation
import random
import math
import csv

random.seed()


# raten der Reaktionen ausrechnen
# state tuple muss mit *state in funktion übergeben werden
def a_0(w, f):
    return int(w * 20)

def a_1(w, f):
    return int(w * f * 0.01)

def a_2(w, f):
    return int(f * 20)

# aSum ausrechnen
def a_Sum(a0, a1, a2):
    return int(a0 + a1 + a2)

# zufallszahl ziehen
def getRandZahl(aSum):
    return random.randint(0, aSum)


# mimimales j bestimmen
def j_set(randZahl, a0, a1, a2):
    if (a0 > randZahl):
        return 0
    elif ( (a0 + a1) > randZahl ):
        return 1
    else:
        return 2

# regel R_j auswählen und neuen state zurückgeben
def r_j(j, w, f):
    if (j == 0):
        return (w+1, f)
    elif (j == 1):
        return (w-1, f+1)
    else:
        return (w, f-1)


def exp(aSum):
    rate = aSum
    y = random.random()
    x = (math.log(1-(1-math.exp(-rate))*y)) / (-rate) 
    return x

def incrementTime(t, x):
    return t + x



def main():
    t = 0
    state = (1000, 1000)
    steps = 0
    stepCount = 0.1

    with open('test3.csv', 'w', newline='') as file:
        writer = csv.writer(file)
        writer.writerow([t, *state])

        while (t < 1.0):
            a0 = a_0(*state)
            a1 = a_1(*state)
            a2 = a_2(*state)

            aSum = a_Sum(a0, a1, a2)

            randZahl = random.randint(0, aSum)

            j = j_set(randZahl, a0, a1, a2)

            state = r_j(j, *state)

            t = incrementTime(t, exp(aSum))

            # schreibt analog zu abb. 1 nur die werten in t 0.1 schritten
            if (t > stepCount):
                writer.writerow([round(t, 1), *state])
                stepCount += 0.1
           
            # ohne die if-bedingung schhreibt diese linie jeden wert
            # writer.writerow([t, *state])

        print(steps, t, state)  

main()
