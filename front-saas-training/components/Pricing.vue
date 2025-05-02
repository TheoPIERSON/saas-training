<template>
  <section class="py-12 bg-gray-50">
    <div class="container mx-auto px-4">
      <div class="text-center mb-12">
        <h2 class="text-3xl font-bold text-gray-900 mb-4">Choose Your Plan</h2>
        <p class="text-lg text-gray-600 max-w-2xl mx-auto">
          Select the perfect plan for your needs. Upgrade or downgrade at any time.
        </p>
      </div>

      <div class="grid md:grid-cols-2 gap-8 max-w-4xl mx-auto">
        <!-- Freemium Plan -->
        <div class="bg-white rounded-lg shadow-lg overflow-hidden">
          <div class="px-6 py-8 border-b border-gray-200">
            <h3 class="text-2xl font-bold text-gray-900 mb-1">Freemium</h3>
            <p class="text-gray-500">Get started for free</p>
            <div class="mt-4">
              <span class="text-4xl font-bold text-gray-900">$0</span>
              <span class="text-gray-500">/month</span>
            </div>
          </div>
          <div class="px-6 py-8">
            <ul class="space-y-4">
              <li class="flex items-start">
                <svg class="h-6 w-6 text-green-500 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                </svg>
                <span>Basic features</span>
              </li>
              <li class="flex items-start">
                <svg class="h-6 w-6 text-green-500 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                </svg>
                <span>Up to 5 projects</span>
              </li>
              <li class="flex items-start">
                <svg class="h-6 w-6 text-green-500 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                </svg>
                <span>Community support</span>
              </li>
            </ul>
            <NuxtLink to="/register" class="block">
              <button
                class="mt-8 w-full py-3 px-4 bg-gray-200 hover:bg-gray-300 text-gray-900 font-medium rounded-md transition duration-300"
              >
                Get Started
              </button>
            </NuxtLink>
          </div>
        </div>

        <!-- Premium Plan -->
        <div class="bg-white rounded-lg shadow-lg overflow-hidden border-2 border-primary">
          <div class="px-6 py-8 border-b border-gray-200 bg-primary-light">
            <span class="inline-block px-3 py-1 text-xs font-semibold text-black bg-secondary rounded-full mb-2"
              >RECOMMENDED</span
            >
            <h3 class="text-2xl font-bold text-gray-900 mb-1">Premium</h3>
            <p class="text-gray-900">For professionals</p>
            <div class="mt-4">
              <span class="text-4xl font-bold text-gray-900">2.99€</span>
              <span class="text-gray-900">/month</span>
            </div>
          </div>
          <div class="px-6 py-8">
            <ul class="space-y-4">
              <li class="flex items-start">
                <svg class="h-6 w-6 text-green-500 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                </svg>
                <span>All Freemium features</span>
              </li>
              <li class="flex items-start">
                <svg class="h-6 w-6 text-green-500 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                </svg>
                <span>Unlimited projects</span>
              </li>
              <li class="flex items-start">
                <svg class="h-6 w-6 text-green-500 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                </svg>
                <span>Priority support</span>
              </li>
              <li class="flex items-start">
                <svg class="h-6 w-6 text-green-500 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                </svg>
                <span>Advanced analytics</span>
              </li>
            </ul>
            <button
              class="mt-8 w-full py-3 px-4 bg-primary hover:bg-primary-dark text-white font-medium rounded-md transition duration-300"
            >
              Upgrade Now
            </button>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "Pricing",
};
</script>
