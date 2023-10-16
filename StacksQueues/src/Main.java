import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    private static class MyStack<T extends Comparable<T>> {
        public T getMin() {
            if (minRegister == null) throw new EmptyStackException();
            return minRegister.data ;
        }

        private int getLength() {
            return this.length;
        }

        private int length = 0;

        private class StackNode<T> {
            private T data;
            private StackNode<T> next;

            public StackNode(T data) {
                this.data = data;
            }

            public String toString() {
                if (null == next) {
                    return data.toString();
                } else {
                    return next.toString() + ", " + data.toString();
                }
            }
        }

        private StackNode<T> top;
        private StackNode<T> minRegister = null;

        public T pop() {
            if (top == null) throw new EmptyStackException();
            T item = top.data;
            top = top.next;

            updateMinAfterPop(item);

            this.length--;

            return item;
        }

        private void updateMinAfterPop(T item) {
            if (minRegister.data == item) {
                minRegister = minRegister.next;
            }
        }

        private void updateMinAfterPush(T item) {
            StackNode<T> m = new StackNode<T>(item);
            if (null == minRegister) {
                m.next = null;
                minRegister = m;
            } else if (item.compareTo(minRegister.data) <= 0) {
                m.next = minRegister;
                minRegister = m;
            }
        }

        public void push (T item) {
            StackNode<T> t = new StackNode<T>(item);
            t.next = top;
            top = t;

            updateMinAfterPush(item);
            length++;
        }

        public T peek() {
            if (top == null) throw new EmptyStackException();
            return top.data;
        }

        public boolean isEmpty() {
            return top == null;
        }

        public String toString() {
            return top.toString();
        }


    }

    private static class SetOfStacks <T extends Comparable<T>> {
        HashMap<Integer, MyStack<T>> stacks = new HashMap<>();
        private final Integer stackSize;
        private Integer numberOfStacks = 0;

        public SetOfStacks() {
            this(100);
        }

        public SetOfStacks(Integer stackSize) {
            this.stackSize = stackSize;
        }

        public void push(T item) {
            if (numberOfStacks == 0) {
                MyStack<T> firstStack = new MyStack<>();
                firstStack.push(item);
                numberOfStacks++;
                stacks.put(numberOfStacks, firstStack);

            } else {
                MyStack<T> latestStack = stacks.get(numberOfStacks);
                if (stackSize == latestStack.length) {
                    MyStack<T> nextStack = new MyStack<>();
                    nextStack.push(item);
                    numberOfStacks++;
                    stacks.put(numberOfStacks, nextStack);
                } else {
                    latestStack.push(item);
                }
            }
        }

        public T pop() {
            MyStack<T> latestStack = stacks.get(numberOfStacks);
            T result = latestStack.pop();

            if (0 == latestStack.length) {
                stacks.remove(numberOfStacks);
                numberOfStacks--;
            }

            return result;
        }

        public T popAt(Integer index) {
            if (stacks.keySet().contains(index)) {
                return stacks.get(index).pop();
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }

        public T peek() {
            MyStack<T> latestStack = stacks.get(numberOfStacks);
            return (T) latestStack.peek();
        }

        public String toString() {
            String result = "";
            for (Integer i = 1; i <= numberOfStacks; i++ ) {
                result += stacks.get(i) + "\n";
            }
            return result;
        }

    }

    private static class MyQueue<T extends Comparable<T>> {
        MyStack<T> end = new MyStack<>();
        MyStack<T> front = new MyStack<>();

        public void add(T item) {
            while (!front.isEmpty()) {
                end.push(front.pop());
            }
            end.push(item);
        }

        public T remove() {
            while (!end.isEmpty()) {
                front.push(end.pop());
            }
            return front.pop();
        }

        public T peek() {
            while (!end.isEmpty()) {
                front.push(end.pop());
            }
            return front.peek();
        }

        public boolean isEmpty() {
            return front.isEmpty() && end.isEmpty();
        }

        public String toString() {
            while (!front.isEmpty()) {
                end.push(front.pop());
            }
            return end.toString();
        }
    }

    private static interface Animal {
        String getName();
        void setName(String name);
        String getAnimalType();
        Integer getArrivalOrder();
        void setArrivalOrder(Integer arrivalOrder);

    }

    private static class Cat implements Animal {
        private String name;
        private final String animalType;
        private Integer arrivalOrder;
        public Cat() {
            this("Unknown");
        }
        public Cat(String name) {
            setName(name);
            this.animalType = "Cat";
        }
        public String getName() {
            return this.name;
        }
        public void setName (String name) {
            this.name = name;
        }

        public String getAnimalType() {
            return this.animalType;
        }
        public Integer getArrivalOrder() {
            return this.arrivalOrder;
        }

        public void setArrivalOrder(Integer arrivalOrder) {
            this.arrivalOrder = arrivalOrder;
        }

        public String toString() {
            return this.arrivalOrder.toString() + " " + this.animalType + ": " + this.name;
        }
    }

    private static class Dog implements Animal {
        private String name;
        private final String animalType;
        private Integer arrivalOrder;
        public Dog() {
            this("Unknown");
        }
        public Dog(String name) {
            setName(name);
            this.animalType = "Dog";
        }
        public String getName() {
            return this.name;
        }
        public void setName (String name) {
            this.name = name;
        }

        public String getAnimalType() {
            return this.animalType;
        }
        public Integer getArrivalOrder() {
            return this.arrivalOrder;
        }
        public void setArrivalOrder(Integer arrivalOrder) {
            this.arrivalOrder = arrivalOrder;
        }

        public String toString() {
            return this.arrivalOrder.toString() + " " + this.animalType + ": " + this.name;
        }

    }

    private static class AnimalShelter {

        private LinkedList<Animal> cats = new LinkedList<>();
        private LinkedList<Animal> dogs = new LinkedList<>();

        private Integer arrivals = 1;

        public void enqueue(Animal animal) {

            animal.setArrivalOrder(arrivals++);

            if (animal.getAnimalType() == "Dog") {
                dogs.push(animal);
            }

            if (animal.getAnimalType() == "Cat") {
                cats.push(animal);
            }

            System.out.println(dogs.peek());
        }

        public Animal dequeueAny() {
            if (cats.getLast().getArrivalOrder() < dogs.getLast().getArrivalOrder()){
                return dequeueCat();
            }
            if (cats.getLast().getArrivalOrder() > dogs.getLast().getArrivalOrder()){
                return dequeueDog();
            }
            return null;
        }

        public Dog dequeueDog() {

            return (Dog) dogs.removeLast();
        }

        public Cat dequeueCat() {
            return (Cat) cats.removeLast();
        }

        @Override
        public String toString() {
            return cats.toString() + "\n" + dogs.toString();
        }
    }

    public static void main(String[] args) {
        MyStack<Integer> a = new MyStack<>();

        a.push(1);
        a.push(2);
        a.push(3);
        a.push(-5);
        a.push(8);
        a.push(9);
        a.push(1);
        a.push(2);

        sortStack(a);
        System.out.println(a);

        AnimalShelter test = new AnimalShelter();
        Cat c1 = new Cat("buster");
        Cat c2 = new Cat("catty");
        Cat c3 = new Cat("dirty");
        Cat c4 = new Cat("eggplang");
        Dog d1 = new Dog("abby");
        Dog d2 = new Dog("becky");
        Dog d3 = new Dog("cathy");

        test.enqueue(d3);
        test.enqueue(c3);
        test.enqueue(d2);
        test.enqueue(d1);
        test.enqueue(c1);
        test.enqueue(c4);
        test.enqueue(c2);

        System.out.println(test);
        System.out.println(test.dequeueAny());
        System.out.println(test);
        System.out.println(test.dequeueDog());
        System.out.println(test);
        System.out.println(test.dequeueCat());
        System.out.println(test);




    }

    /**
     * Assumptions:
     * Can use additional helper stack, but only ops available are:
     *  push, pop, peek, isEmpty
     *
     * @param input
     */
    private static void sortStack(MyStack<Integer> input) {
        MyStack<Integer> temp1 = new MyStack<>();
        MyStack<Integer> temp2 = new MyStack<>();
        Integer nextMax = input.pop();

        while (!input.isEmpty()) {
            if (nextMax < input.peek()) {
                temp1.push(nextMax);
                nextMax = input.pop();
            } else {
                temp1.push(input.pop());
            }
        }

        input.push(nextMax);

        nextMax = temp1.pop();

        while (!temp1.isEmpty() ||  !temp2.isEmpty()) {
            while (!temp1.isEmpty()) {
                if (nextMax < temp1.peek()) {
                    temp2.push(nextMax);
                    nextMax = temp1.pop();
                } else {
                    temp2.push(temp1.pop());
                }
            }

            input.push(nextMax);

            if (!temp2.isEmpty()) {
                nextMax = temp2.pop();
            }

            while (!temp2.isEmpty()) {
                if (nextMax < temp2.peek()) {
                    temp1.push(nextMax);
                    nextMax = temp2.pop();
                } else {
                    temp1.push(temp2.pop());
                }
            }

            input.push(nextMax);

            if (!temp1.isEmpty()) {
                nextMax = temp1.pop();
            }
        }
        input.push(nextMax);
    }

    private static void sortStack2(MyStack<Integer> input) {
        MyStack<Integer> sorted = new MyStack<>();
        Integer temp;

        sorted.push(input.pop());
        temp = input.pop();

        while (!input.isEmpty()) {
            if (sorted.peek() > temp) {
                input.push(sorted.pop());
            } else {
                sorted.push(temp);
            }
            temp = input.pop();
        }


        while (!sorted.isEmpty()) {
            input.push(sorted.pop());
        }
    }
}