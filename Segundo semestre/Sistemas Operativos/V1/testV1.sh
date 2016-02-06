./TeacherSimulatorV1 a programTooBig programWithOnlySize programWithNegativeSize emptyProgram notExistingProgram > salida-Teacher
./Simulator a programTooBig programWithOnlySize programWithNegativeSize emptyProgram notExistingProgram > salida
echo "Test 1"
diff salida-Teacher salida
rm salida-Teacher salida

./TeacherSimulatorV1 a testProgram1_V1 testProgram2_V1 testProgram1_V1 testProgram2_V1 > salida-Teacher
./Simulator a testProgram1_V1 testProgram2_V1 testProgram1_V1 testProgram2_V1 > salida
echo "Test 2"
diff salida-Teacher salida
rm salida-Teacher salida

./TeacherSimulatorV1 a testProgram1_V1 testProgram2_V1 testProgram1_V1 > salida-Teacher
./Simulator a testProgram1_V1 testProgram2_V1 testProgram1_V1 > salida
echo "Test 3"
diff salida-Teacher salida
rm salida-Teacher salida

./TeacherSimulatorV1 a testProgram3_V1 testProgram4_V1 > salida-Teacher
./Simulator a testProgram3_V1 testProgram4_V1 > salida
echo "Test 4"
diff salida-Teacher salida
rm salida-Teacher salida

./TeacherSimulatorV1 a testProgram5_V1 testProgram6_V1 testProgram4_V1 > salida-Teacher
./Simulator a testProgram5_V1 testProgram6_V1 testProgram4_V1 > salida
echo "Test 5"
diff salida-Teacher salida
rm salida-Teacher salida

