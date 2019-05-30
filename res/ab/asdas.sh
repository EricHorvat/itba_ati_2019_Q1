# Rename all *.txt to *.text
counter=6
while [ $counter -le 21 ]
do
echo $counter
    lcounter=counter-5
    mv -- "./Frame$counter.ppm" "./Frame$lcounter.ppm"
done
