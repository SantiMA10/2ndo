./TeacherSimulatorV1 a testProgram1_V1 testProgram2_V1 testProgram1_V1 > salida-Teacher
./Simulator a testProgram1_V1 testProgram2_V1 testProgram1_V1 > salida
echo "Test 1"
diff salida-Teacher salida
rm salida-Teacher salida
