import matplotlib.pyplot as plt
import csv

x = []
y = []
z = []

with open('test2.csv', 'r') as csvfile:
    plots = csv.reader(csvfile, delimiter=',')
    for row in plots:
        x.append(row[0])
        y.append(float(row[1]))
        z.append(float(row[2]))

plt.plot(x,y,z,marker='o')

plt.title("Simulation of trees and fire")

plt.xlabel("Simulation Time")
plt.ylabel("Number of Objects")

plt.show()
