package edu.kamshanski.shared.model.toy

import java.util.*

data class Toy(val id: UUID, val type: ToyType)

enum class ToyType {
    DOLL, BALL, CAR, ROCKET, STAR;
}