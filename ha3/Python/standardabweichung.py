import csv
import numpy as np

values = []

with open('aufgabe2.csv', 'r') as file:
    rows = csv.reader(file)
    for row in rows:
        values.append(int(row[0]))

print(np.std(values))
# prints: 1451.3697510972179
