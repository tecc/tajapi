/*
 * Copyright (c) tecc, 2020.
 * This project is licensed under tecc's license.
 */

package me.tecc.tajapi.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * A chain of tasks.
 * Makes for easier asynchronous/synchronous stuff.
 */
public class TaskChain {
    /**
     * The list of tasks to execute at {@link #run()}.
     */
    protected List<Task> tasks;
    /**
     * Determines whether or not to run the tasks immediately.
     */
    boolean runImmediately;

    /**
     * Initializes a task chain.
     * Does not run tasks immediately.
     */
    public TaskChain() {
        this(false);
    }
    /**
     * Initializes a task chain.
     * @param runImmediately Whether to run the tasks as soon as they are added.
     */
    public TaskChain(boolean runImmediately) {
        tasks = new LinkedList<>();
    }

    /**
     * Adds a task to the list of tasks to execute.
     * @param task The task to add.
     */
    public void addTask(Task task) {
        if (runImmediately)
            task.run();
        tasks.add(task);
    }

    /**
     * Removes a task from the list of tasks to execute.
     * @param task The task to remove.
     */
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Clears the list of tasks to execute.
     */
    public void clear() {
        tasks.clear();
    }

    /**
     * Adds a runnable as an asynchronous task.
     * This continues running other tasks while the runnable is running.
     * @param runnable The runnable to add
     */
    public void async(Runnable runnable) {
        addTask(new Task(runnable, true, false));
    }

    /**
     * Adds a runnable as an asynchronous task.
     * This waits until the runnable has run before running any other tasks.
     * @param runnable The runnable to add.
     */
    public void asyncWait(Runnable runnable) {
        addTask(new Task(runnable, true));
    }

    /**
     * Adds a runnable as a synchronous task.
     * Effectively the same as running it directly.
     * @param runnable The runnable to run.
     */
    public void sync(Runnable runnable) {
        addTask(new Task(runnable, false));
    }

    /**
     * Runs all tasks that are in the task list.
     */
    public void run() {
        tasks.forEach(Task::run);
    }

    /**
     * A task.
     * Contains all data necessary to run it without any hassle.
     */
    public static class Task {
        /**
         * The runnable that this task will run.
         */
        public Runnable runnable;
        /**
         * Determines whether or not to run the task asynchronously.
         */
        public boolean asynchronous;
        /**
         * Determines whether or not to wait if the task is asynchronous.
         * If the task is synchronous, this property does not matter.
         */
        public boolean wait;

        /**
         * Creates a new task.
         * @param runnable The runnable for this task.
         * @param asynchronous Whether or not to run it asynchronously.
         */
        public Task(Runnable runnable, boolean asynchronous) {
            this(runnable, asynchronous, true);
        }

        /**
         * Creates a new task.
         * @param runnable The runnable for this task.
         * @param asynchronous Whether or not to run it asynchronously.
         * @param wait Whether or not to wait.
         */
        public Task(Runnable runnable, boolean asynchronous, boolean wait) {
            this.runnable = runnable;
            this.asynchronous = asynchronous;
            this.wait = wait;
        }

        /**
         * Runs this task, using the previously configured properties.
         */
        public void run() {
            if (!asynchronous) {
                runnable.run();
                return;
            }
            Future<Void> future = CompletableFuture.runAsync(runnable);

            if (wait) {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
