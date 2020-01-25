<template>
  <q-layout view="lhh lpr lFf" >
    <q-header elevated>
        <q-toolbar class="bg-teal-8">
        <q-btn
          flat
          dense
          round
          @click="leftDrawerOpen = !leftDrawerOpen"
          icon="menu"
          aria-label="Menu"
        />

        <q-toolbar-title >
          Admin
        </q-toolbar-title>

        <!-- <div class="text-white">Admin v{{ $q.version }}</div> -->
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      show-if-above
      bordered
      content-class="bg-teal-4"
    >
      <q-list class="text-white">
        <q-item-label
        header
        class="text-center bg-teal text-white text-h6"
        >MENU</q-item-label>
        <q-item clickable tag="a" href="/index">
          <q-item-section avatar>
            <q-icon name="home"/>
          </q-item-section>
          <q-item-section >
            <q-item-label>DASHBOARD</q-item-label>
          </q-item-section>
        </q-item>

          <q-item-section>
            <q-btn-dropdown
            icon="school"
            class="q-mx-lg"
            flat=""
            align="center"
            label="GURU"
            >
              <q-list>
                <q-item clickable tag="a" href="/inputguru">
                  <q-item-section>
                    <q-item-label>Input Data Guru</q-item-label>
                  </q-item-section>
                </q-item>

                <q-item clickable tag="a" href="/lihatguru">
                  <q-item-section>
                    <q-item-label>Cek Data</q-item-label>
                  </q-item-section>
                </q-item>
              </q-list>
            </q-btn-dropdown>
            <q-btn-dropdown
            icon="person"
            class="q-mx-lg"
            flat
            align="center"
            label="SISWA"
            >
              <q-list>
                <q-item clickable tag="a" href="/inputsiswa">
                  <q-item-section>
                    <q-item-label>Input Data Siswa</q-item-label>
                  </q-item-section>
                </q-item>

                <q-item clickable tag="a" href="/lihatsiswa">
                  <q-item-section>
                    <q-item-label>Cek Data</q-item-label>
                  </q-item-section>
                </q-item>
              </q-list>
          </q-btn-dropdown>
          </q-item-section>
          <q-btn-dropdown
            icon="book"
            class="q-mx-lg"
            flat
            align="center"
            label="MATA KULIAH"
            >
              <q-list>
                <q-item clickable tag="a" href="/inputjadwal">
                  <q-item-section>
                    <q-item-label>Input Jadwal</q-item-label>
                  </q-item-section>
                </q-item>

                <q-item clickable tag="a" href="/lihatjadwal">
                  <q-item-section>
                    <q-item-label>Cek Jadwal</q-item-label>
                  </q-item-section>
                </q-item>
              </q-list>
          </q-btn-dropdown>
          <q-btn-dropdown
            icon="money"
            class="q-mx-lg"
            flat
            align="center"
            label="SPP"
            >
              <q-list>
                <q-item clickable tag="a" href="/inputSPP">
                  <q-item-section>
                    <q-item-label>Input SPP</q-item-label>
                  </q-item-section>
                </q-item>

                <q-item clickable tag="a" href="/lihatSPP">
                  <q-item-section>
                    <q-item-label>Cek SPP</q-item-label>
                  </q-item-section>
                </q-item>
              </q-list>
          </q-btn-dropdown>

        <q-item clickable tag="a" href="/">
          <q-item-section avatar>
            <q-icon name="logout" />
          </q-item-section>
          <q-item-section>
            <q-item-label>LOGOUT</q-item-label>
            <!-- <q-item-label caption class="text-indigo">Keluar lah sebelum di keluarkan</q-item-label> -->
          </q-item-section>
        </q-item>
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: 'MyLayout',

  data () {
    return {
      leftDrawerOpen: false
    }
  }
}
</script>
