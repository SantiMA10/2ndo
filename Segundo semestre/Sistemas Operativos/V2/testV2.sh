echo "Test 1 Start - If empty, good news!"
./TeacherSimulatorV2 a testProgram0_V2 > salida-Teacher
./Simulator a testProgram0_V2 > salida
diff salida-Teacher salida
echo "Test 1 End"
rm salida-Teacher salida
