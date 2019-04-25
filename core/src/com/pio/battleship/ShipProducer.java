package com.pio.battleship;

public class ShipProducer {

    public enum FactoryType {

        SmallBoatFactory,
        CorvetteFactory,
        DestroyerFactory,
        AircraftCarrierFactory

        }

    public static ShipFactory getFactory ( FactoryType Type ) {

        switch ( Type ) {

            case SmallBoatFactory: return new SmallBoatFactory();
            case CorvetteFactory: return new CorvetteFactory();
            case DestroyerFactory: return new DestroyerFactory();
            case AircraftCarrierFactory: return new AircraftCarrierFactory(); }

        return null; }

    }
